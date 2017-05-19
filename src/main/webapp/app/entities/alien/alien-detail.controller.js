(function() {
    'use strict';

    angular
        .module('alienApp')
        .controller('AlienDetailController', AlienDetailController);

    AlienDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Alien'];

    function AlienDetailController($scope, $rootScope, $stateParams, previousState, entity, Alien) {
        var vm = this;

        vm.alien = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('alienApp:alienUpdate', function(event, result) {
            vm.alien = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
