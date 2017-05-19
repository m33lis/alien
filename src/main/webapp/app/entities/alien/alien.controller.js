(function() {
    'use strict';

    angular
        .module('alienApp')
        .controller('AlienController', AlienController);

    AlienController.$inject = ['Alien'];

    function AlienController(Alien) {

        var vm = this;

        vm.aliens = [];

        loadAll();

        function loadAll() {
            Alien.query(function(result) {
                vm.aliens = result;
                vm.searchQuery = null;
            });
        }
    }
})();
