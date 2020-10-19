import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DemoTestModule } from '../../../test.module';
import { MpHotspotMpUpdateComponent } from 'app/entities/mp-hotspot-mp/mp-hotspot-mp-update.component';
import { MpHotspotMpService } from 'app/entities/mp-hotspot-mp/mp-hotspot-mp.service';
import { MpHotspotMp } from 'app/shared/model/mp-hotspot-mp.model';

describe('Component Tests', () => {
  describe('MpHotspotMp Management Update Component', () => {
    let comp: MpHotspotMpUpdateComponent;
    let fixture: ComponentFixture<MpHotspotMpUpdateComponent>;
    let service: MpHotspotMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DemoTestModule],
        declarations: [MpHotspotMpUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MpHotspotMpUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MpHotspotMpUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MpHotspotMpService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MpHotspotMp(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MpHotspotMp();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
