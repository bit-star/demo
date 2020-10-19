import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IMpHotspotMp } from 'app/shared/model/mp-hotspot-mp.model';

type EntityResponseType = HttpResponse<IMpHotspotMp>;
type EntityArrayResponseType = HttpResponse<IMpHotspotMp[]>;

@Injectable({ providedIn: 'root' })
export class MpHotspotMpService {
  public resourceUrl = SERVER_API_URL + 'api/mp-hotspots';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/mp-hotspots';

  constructor(protected http: HttpClient) {}

  create(mpHotspot: IMpHotspotMp): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(mpHotspot);
    return this.http
      .post<IMpHotspotMp>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(mpHotspot: IMpHotspotMp): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(mpHotspot);
    return this.http
      .put<IMpHotspotMp>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMpHotspotMp>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMpHotspotMp[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMpHotspotMp[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(mpHotspot: IMpHotspotMp): IMpHotspotMp {
    const copy: IMpHotspotMp = Object.assign({}, mpHotspot, {
      addTime: mpHotspot.addTime && mpHotspot.addTime.isValid() ? mpHotspot.addTime.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.addTime = res.body.addTime ? moment(res.body.addTime) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((mpHotspot: IMpHotspotMp) => {
        mpHotspot.addTime = mpHotspot.addTime ? moment(mpHotspot.addTime) : undefined;
      });
    }
    return res;
  }
}
