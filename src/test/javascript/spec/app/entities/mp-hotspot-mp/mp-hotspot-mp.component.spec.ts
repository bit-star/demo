import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DemoTestModule } from '../../../test.module';
import { MpHotspotMpComponent } from 'app/entities/mp-hotspot-mp/mp-hotspot-mp.component';
import { MpHotspotMpService } from 'app/entities/mp-hotspot-mp/mp-hotspot-mp.service';
import { MpHotspotMp } from 'app/shared/model/mp-hotspot-mp.model';

describe('Component Tests', () => {
  describe('MpHotspotMp Management Component', () => {
    let comp: MpHotspotMpComponent;
    let fixture: ComponentFixture<MpHotspotMpComponent>;
    let service: MpHotspotMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DemoTestModule],
        declarations: [MpHotspotMpComponent],
      })
        .overrideTemplate(MpHotspotMpComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MpHotspotMpComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MpHotspotMpService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MpHotspotMp(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.mpHotspots && comp.mpHotspots[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
