import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMpHotspotMp, MpHotspotMp } from 'app/shared/model/mp-hotspot-mp.model';
import { MpHotspotMpService } from './mp-hotspot-mp.service';
import { MpHotspotMpComponent } from './mp-hotspot-mp.component';
import { MpHotspotMpDetailComponent } from './mp-hotspot-mp-detail.component';
import { MpHotspotMpUpdateComponent } from './mp-hotspot-mp-update.component';

@Injectable({ providedIn: 'root' })
export class MpHotspotMpResolve implements Resolve<IMpHotspotMp> {
  constructor(private service: MpHotspotMpService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMpHotspotMp> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((mpHotspot: HttpResponse<MpHotspotMp>) => {
          if (mpHotspot.body) {
            return of(mpHotspot.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MpHotspotMp());
  }
}

export const mpHotspotRoute: Routes = [
  {
    path: '',
    component: MpHotspotMpComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'demoApp.mpHotspot.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MpHotspotMpDetailComponent,
    resolve: {
      mpHotspot: MpHotspotMpResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'demoApp.mpHotspot.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MpHotspotMpUpdateComponent,
    resolve: {
      mpHotspot: MpHotspotMpResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'demoApp.mpHotspot.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MpHotspotMpUpdateComponent,
    resolve: {
      mpHotspot: MpHotspotMpResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'demoApp.mpHotspot.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
