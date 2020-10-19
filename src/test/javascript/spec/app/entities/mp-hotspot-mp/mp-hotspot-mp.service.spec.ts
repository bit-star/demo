import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { MpHotspotMpService } from 'app/entities/mp-hotspot-mp/mp-hotspot-mp.service';
import { IMpHotspotMp, MpHotspotMp } from 'app/shared/model/mp-hotspot-mp.model';

describe('Service Tests', () => {
  describe('MpHotspotMp Service', () => {
    let injector: TestBed;
    let service: MpHotspotMpService;
    let httpMock: HttpTestingController;
    let elemDefault: IMpHotspotMp;
    let expectedResult: IMpHotspotMp | IMpHotspotMp[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MpHotspotMpService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new MpHotspotMp(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            addTime: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a MpHotspotMp', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            addTime: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            addTime: currentDate,
          },
          returnedFromService
        );

        service.create(new MpHotspotMp()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a MpHotspotMp', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            imageUrl: 'BBBBBB',
            pathUrl: 'BBBBBB',
            addTime: currentDate.format(DATE_TIME_FORMAT),
            orderNum: 1,
            note: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            addTime: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of MpHotspotMp', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            imageUrl: 'BBBBBB',
            pathUrl: 'BBBBBB',
            addTime: currentDate.format(DATE_TIME_FORMAT),
            orderNum: 1,
            note: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            addTime: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a MpHotspotMp', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
