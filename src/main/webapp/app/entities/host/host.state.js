(function() {
    'use strict';

    angular
        .module('alienApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('host', {
            parent: 'entity',
            url: '/host',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Hosts'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/host/hosts.html',
                    controller: 'HostController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('host-detail', {
            parent: 'host',
            url: '/host/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Host'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/host/host-detail.html',
                    controller: 'HostDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Host', function($stateParams, Host) {
                    return Host.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'host',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('host-detail.edit', {
            parent: 'host-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/host/host-dialog.html',
                    controller: 'HostDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Host', function(Host) {
                            return Host.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('host.new', {
            parent: 'host',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/host/host-dialog.html',
                    controller: 'HostDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                dna: null,
                                avatar: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('host', null, { reload: 'host' });
                }, function() {
                    $state.go('host');
                });
            }]
        })
        .state('host.edit', {
            parent: 'host',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/host/host-dialog.html',
                    controller: 'HostDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Host', function(Host) {
                            return Host.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('host', null, { reload: 'host' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('host.delete', {
            parent: 'host',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/host/host-delete-dialog.html',
                    controller: 'HostDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Host', function(Host) {
                            return Host.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('host', null, { reload: 'host' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
