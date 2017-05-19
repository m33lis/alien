(function() {
    'use strict';

    angular
        .module('alienApp')
        .controller('HostDialogController', HostDialogController);

    HostDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Host'];

    function HostDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Host) {
        var vm = this;

        vm.host = entity;
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
            if (vm.host.id !== null) {
                Host.update(vm.host, onSaveSuccess, onSaveError);
            } else {
                Host.save(vm.host, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('alienApp:hostUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
