(function() {
    'use strict';
    angular
        .module('alienApp')
        .factory('Host', Host);

    Host.$inject = ['$resource'];

    function Host ($resource) {
        var resourceUrl =  'api/hosts/:id';

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
