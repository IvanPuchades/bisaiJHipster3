'use strict';

describe('Controller Tests', function() {

    describe('Torneo Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockTorneo, MockJuego, MockClasificacion;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockTorneo = jasmine.createSpy('MockTorneo');
            MockJuego = jasmine.createSpy('MockJuego');
            MockClasificacion = jasmine.createSpy('MockClasificacion');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Torneo': MockTorneo,
                'Juego': MockJuego,
                'Clasificacion': MockClasificacion
            };
            createController = function() {
                $injector.get('$controller')("TorneoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'bisaiApp:torneoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
