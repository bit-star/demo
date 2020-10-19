import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMpHotspotMp } from 'app/shared/model/mp-hotspot-mp.model';

@Component({
  selector: 'jhi-mp-hotspot-mp-detail',
  templateUrl: './mp-hotspot-mp-detail.component.html',
})
export class MpHotspotMpDetailComponent implements OnInit {
  mpHotspot: IMpHotspotMp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mpHotspot }) => (this.mpHotspot = mpHotspot));
  }

  previousState(): void {
    window.history.back();
  }
}
