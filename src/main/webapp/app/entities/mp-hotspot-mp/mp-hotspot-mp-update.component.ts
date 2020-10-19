import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMpHotspotMp, MpHotspotMp } from 'app/shared/model/mp-hotspot-mp.model';
import { MpHotspotMpService } from './mp-hotspot-mp.service';
import { IFmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';
import { FmpSubCompanyMpService } from 'app/entities/fmp-sub-company-mp/fmp-sub-company-mp.service';

@Component({
  selector: 'jhi-mp-hotspot-mp-update',
  templateUrl: './mp-hotspot-mp-update.component.html',
})
export class MpHotspotMpUpdateComponent implements OnInit {
  isSaving = false;
  fmpsubcompanies: IFmpSubCompanyMp[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    imageUrl: [],
    pathUrl: [],
    addTime: [],
    orderNum: [],
    note: [],
    fmpSubCompany: [],
  });

  constructor(
    protected mpHotspotService: MpHotspotMpService,
    protected fmpSubCompanyService: FmpSubCompanyMpService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mpHotspot }) => {
      if (!mpHotspot.id) {
        const today = moment().startOf('day');
        mpHotspot.addTime = today;
      }

      this.updateForm(mpHotspot);

      this.fmpSubCompanyService.query().subscribe((res: HttpResponse<IFmpSubCompanyMp[]>) => (this.fmpsubcompanies = res.body || []));
    });
  }

  updateForm(mpHotspot: IMpHotspotMp): void {
    this.editForm.patchValue({
      id: mpHotspot.id,
      name: mpHotspot.name,
      imageUrl: mpHotspot.imageUrl,
      pathUrl: mpHotspot.pathUrl,
      addTime: mpHotspot.addTime ? mpHotspot.addTime.format(DATE_TIME_FORMAT) : null,
      orderNum: mpHotspot.orderNum,
      note: mpHotspot.note,
      fmpSubCompany: mpHotspot.fmpSubCompany,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const mpHotspot = this.createFromForm();
    if (mpHotspot.id !== undefined) {
      this.subscribeToSaveResponse(this.mpHotspotService.update(mpHotspot));
    } else {
      this.subscribeToSaveResponse(this.mpHotspotService.create(mpHotspot));
    }
  }

  private createFromForm(): IMpHotspotMp {
    return {
      ...new MpHotspotMp(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      imageUrl: this.editForm.get(['imageUrl'])!.value,
      pathUrl: this.editForm.get(['pathUrl'])!.value,
      addTime: this.editForm.get(['addTime'])!.value ? moment(this.editForm.get(['addTime'])!.value, DATE_TIME_FORMAT) : undefined,
      orderNum: this.editForm.get(['orderNum'])!.value,
      note: this.editForm.get(['note'])!.value,
      fmpSubCompany: this.editForm.get(['fmpSubCompany'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMpHotspotMp>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IFmpSubCompanyMp): any {
    return item.id;
  }
}
