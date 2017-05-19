(function() {
    'use strict';

    angular
        .module('alienApp')
        .controller('AlienDialogController', AlienDialogController);

    AlienDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Alien'];

    function AlienDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Alien) {
        var vm = this;

        vm.alien = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.alien.id !== null) {
                Alien.update(vm.alien, onSaveSuccess, onSaveError);
            } else {
                Alien.save(vm.alien, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('alienApp:alienUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
