(function() {
    'use strict';

    angular
        .module('alienApp')
        .controller('HostDetailController', HostDetailController);

    HostDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Host'];

    function HostDetailController($scope, $rootScope, $stateParams, previousState, entity, Host) {
        var vm = this;

        vm.host = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('alienApp:hostUpdate', function(event, result) {
            vm.host = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
