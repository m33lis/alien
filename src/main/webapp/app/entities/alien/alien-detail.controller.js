(function() {
    'use strict';

    angular
        .module('alienApp')
        .controller('AlienDetailController', AlienDetailController);

    AlienDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Alien', 'hosts'];

    function AlienDetailController($scope, $rootScope, $stateParams, previousState, entity, Alien, hosts) {
        var vm = this;

        console.log("DEBUG:: Here are the hosts: ", hosts);

        vm.alien = entity;
        vm.hosts = hosts;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('alienApp:alienUpdate', function(event, result) {
            vm.alien = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
