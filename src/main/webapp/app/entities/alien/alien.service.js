(function() {
    'use strict';
    angular
        .module('alienApp')
        .factory('Alien', Alien);

    Alien.$inject = ['$resource'];

    function Alien ($resource) {
        var resourceUrl =  'api/aliens/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
