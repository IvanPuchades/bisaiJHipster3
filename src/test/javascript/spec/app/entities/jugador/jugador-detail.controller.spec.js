'use strict';

describe('Controller Tests', function() {

    describe('Jugador Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockJugador, MockUser, MockEquipo;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockJugador = jasmine.createSpy('MockJugador');
            MockUser = jasmine.createSpy('MockUser');
            MockEquipo = jasmine.createSpy('MockEquipo');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Jugador': MockJugador,
                'User': MockUser,
                'Equipo': MockEquipo
            };
            createController = function() {
                $injector.get('$controller')("JugadorDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'bisaiApp:jugadorUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
