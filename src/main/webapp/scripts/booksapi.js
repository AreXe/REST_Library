angular.module('booksapi', [])
    .controller('bookslist', function($scope, $http) {
        return $http.get(location.origin + '/library-api/api/books').
        then(function(response) {
            $scope.list = response.data;
        });
    });