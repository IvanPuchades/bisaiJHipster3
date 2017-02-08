(function() {
    'use strict';

    angular
        .module('bisaiApp')
        .controller('JugadorDetailController', JugadorDetailController);

    JugadorDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Jugador', 'User', 'Equipo'];

    function JugadorDetailController($scope, $rootScope, $stateParams, previousState, entity, Jugador, User, Equipo) {
        var vm = this;

        vm.jugador = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('bisaiApp:jugadorUpdate', function(event, result) {
            vm.jugador = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
