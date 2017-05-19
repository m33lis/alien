(function() {
    'use strict';

    angular
        .module('alienApp')
        .controller('HostController', HostController);

    HostController.$inject = ['Host'];

    function HostController(Host) {

        var vm = this;

        vm.hosts = [];

        loadAll();

        function loadAll() {
            Host.query(function(result) {
                vm.hosts = result;
                vm.searchQuery = null;
            });
        }
    }
})();
