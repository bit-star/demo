import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMpHotspotMp } from 'app/shared/model/mp-hotspot-mp.model';
import { MpHotspotMpService } from './mp-hotspot-mp.service';

@Component({
  templateUrl: './mp-hotspot-mp-delete-dialog.component.html',
})
export class MpHotspotMpDeleteDialogComponent {
  mpHotspot?: IMpHotspotMp;

  constructor(
    protected mpHotspotService: MpHotspotMpService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.mpHotspotService.delete(id).subscribe(() => {
      this.eventManager.broadcast('mpHotspotListModification');
      this.activeModal.close();
    });
  }
}
