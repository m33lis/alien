/**
 * Created by m3l on 19.05.17.
 */
(function () {
    'use strict';

    angular
        .module('alienApp')
        .factory('MatchService', Match);

    Match.$inject = ['$resource'];

    function Match ($resource) {
        var service = $resource('api/match/:alienDna/:hostDna', {}, {
            'get' : { method: 'GET',
                    transformResponse: function (data) {
                        return  angular.fromJson(data);
                        }
                    }
        });

        return service;
    }
})();
