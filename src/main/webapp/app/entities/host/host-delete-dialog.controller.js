(function() {
    'use strict';

    angular
        .module('alienApp')
        .controller('HostDeleteController',HostDeleteController);

    HostDeleteController.$inject = ['$uibModalInstance', 'entity', 'Host'];

    function HostDeleteController($uibModalInstance, entity, Host) {
        var vm = this;

        vm.host = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Host.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
