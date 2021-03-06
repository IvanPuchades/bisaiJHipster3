(function() {
    'use strict';

    angular
        .module('bisaiApp')
        .controller('JuegoDetailController', JuegoDetailController);

    JuegoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Juego', 'Torneo'];

    function JuegoDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Juego, Torneo) {
        var vm = this;

        vm.juego = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('bisaiApp:juegoUpdate', function(event, result) {
            vm.juego = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
