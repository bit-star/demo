import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DemoTestModule } from '../../../test.module';
import { MpHotspotMpDetailComponent } from 'app/entities/mp-hotspot-mp/mp-hotspot-mp-detail.component';
import { MpHotspotMp } from 'app/shared/model/mp-hotspot-mp.model';

describe('Component Tests', () => {
  describe('MpHotspotMp Management Detail Component', () => {
    let comp: MpHotspotMpDetailComponent;
    let fixture: ComponentFixture<MpHotspotMpDetailComponent>;
    const route = ({ data: of({ mpHotspot: new MpHotspotMp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DemoTestModule],
        declarations: [MpHotspotMpDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MpHotspotMpDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MpHotspotMpDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load mpHotspot on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mpHotspot).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
