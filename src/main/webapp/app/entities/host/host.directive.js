/**
 * Created by m3l on 19.05.17.
 */
(function () {
    'use strict';

    var hostComponent = {
        template: '<div style="float: left; padding: 5px;" ng-click="vm.match()"><span><img src="{{vm.host.avatar}}" style="max-height: 100px" /></span>' +
                    '<div>{{vm.host.name}}</div>' +
                    '<div>({{vm.host.dna}})</div>' +
                   '</div>',
        controllerAs: 'vm',
        bindings: {
            alien: '<',
            host: '<'
        },
        controller: HostComponentController
    };

    angular
        .module('alienApp')
        .component('host', hostComponent);

    HostComponentController.$inject = ['MatchService'];

    function HostComponentController(MatchService) {
        var vm = this;

        vm.match = match;

        function match() {
            var queryObj = {
                            alienDna: [vm.alien.dnaOne, vm.alien.dnaTwo],
                            hostDna: vm.host.dna
            };
            MatchService.get(queryObj, function (res) {
                alert(res.code+", "+res.message);
            });
        }
    }
})();
