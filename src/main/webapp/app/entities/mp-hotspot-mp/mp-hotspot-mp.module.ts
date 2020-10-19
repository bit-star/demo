import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DemoSharedModule } from 'app/shared/shared.module';
import { MpHotspotMpComponent } from './mp-hotspot-mp.component';
import { MpHotspotMpDetailComponent } from './mp-hotspot-mp-detail.component';
import { MpHotspotMpUpdateComponent } from './mp-hotspot-mp-update.component';
import { MpHotspotMpDeleteDialogComponent } from './mp-hotspot-mp-delete-dialog.component';
import { mpHotspotRoute } from './mp-hotspot-mp.route';

@NgModule({
  imports: [DemoSharedModule, RouterModule.forChild(mpHotspotRoute)],
  declarations: [MpHotspotMpComponent, MpHotspotMpDetailComponent, MpHotspotMpUpdateComponent, MpHotspotMpDeleteDialogComponent],
  entryComponents: [MpHotspotMpDeleteDialogComponent],
})
export class DemoMpHotspotMpModule {}
