(function() {
    'use strict';

    angular
        .module('bisaiApp')
        .controller('TorneoDialogController', TorneoDialogController);

    TorneoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Torneo', 'Juego', 'Clasificacion'];

    function TorneoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Torneo, Juego, Clasificacion) {
        var vm = this;

        vm.torneo = entity;
        vm.clear = clear;
        vm.save = save;
        vm.juegos = Juego.query();
        vm.clasificacions = Clasificacion.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.torneo.id !== null) {
                Torneo.update(vm.torneo, onSaveSuccess, onSaveError);
            } else {
                Torneo.save(vm.torneo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('bisaiApp:torneoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
