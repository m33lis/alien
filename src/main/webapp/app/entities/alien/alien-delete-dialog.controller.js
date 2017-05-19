(function() {
    'use strict';

    angular
        .module('alienApp')
        .controller('AlienDeleteController',AlienDeleteController);

    AlienDeleteController.$inject = ['$uibModalInstance', 'entity', 'Alien'];

    function AlienDeleteController($uibModalInstance, entity, Alien) {
        var vm = this;

        vm.alien = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Alien.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
