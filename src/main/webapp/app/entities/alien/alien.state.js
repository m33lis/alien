(function() {
    'use strict';

    angular
        .module('alienApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('alien', {
            parent: 'entity',
            url: '/alien',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Aliens'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/alien/aliens.html',
                    controller: 'AlienController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('alien-detail', {
            parent: 'alien',
            url: '/alien/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Alien'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/alien/alien-detail.html',
                    controller: 'AlienDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Alien', function($stateParams, Alien) {
                    return Alien.get({id : $stateParams.id}).$promise;
                }],
                hosts: [ 'Host', function (Host) {
                    return Host.query().$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'alien',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('alien-detail.edit', {
            parent: 'alien-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/alien/alien-dialog.html',
                    controller: 'AlienDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Alien', function(Alien) {
                            return Alien.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('alien.new', {
            parent: 'alien',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/alien/alien-dialog.html',
                    controller: 'AlienDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                dnaOne: null,
                                dnaTwo: null,
                                avatar: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('alien', null, { reload: 'alien' });
                }, function() {
                    $state.go('alien');
                });
            }]
        })
        .state('alien.edit', {
            parent: 'alien',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/alien/alien-dialog.html',
                    controller: 'AlienDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Alien', function(Alien) {
                            return Alien.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('alien', null, { reload: 'alien' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('alien.delete', {
            parent: 'alien',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/alien/alien-delete-dialog.html',
                    controller: 'AlienDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Alien', function(Alien) {
                            return Alien.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('alien', null, { reload: 'alien' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
