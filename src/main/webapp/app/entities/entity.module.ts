import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'region',
        loadChildren: './region/region.module#DemoRegionModule'
      },
      {
        path: 'country',
        loadChildren: './country/country.module#DemoCountryModule'
      },
      {
        path: 'location',
        loadChildren: './location/location.module#DemoLocationModule'
      },
      {
        path: 'department',
        loadChildren: './department/department.module#DemoDepartmentModule'
      },
      {
        path: 'task',
        loadChildren: './task/task.module#DemoTaskModule'
      },
      {
        path: 'employee',
        loadChildren: './employee/employee.module#DemoEmployeeModule'
      },
      {
        path: 'job',
        loadChildren: './job/job.module#DemoJobModule'
      },
      {
        path: 'job-history',
        loadChildren: './job-history/job-history.module#DemoJobHistoryModule'
      },
      {
        path: 'region',
        loadChildren: './region/region.module#DemoRegionModule'
      },
      {
        path: 'country',
        loadChildren: './country/country.module#DemoCountryModule'
      },
      {
        path: 'location',
        loadChildren: './location/location.module#DemoLocationModule'
      },
      {
        path: 'department',
        loadChildren: './department/department.module#DemoDepartmentModule'
      },
      {
        path: 'task',
        loadChildren: './task/task.module#DemoTaskModule'
      },
      {
        path: 'employee',
        loadChildren: './employee/employee.module#DemoEmployeeModule'
      },
      {
        path: 'job',
        loadChildren: './job/job.module#DemoJobModule'
      },
      {
        path: 'job-history',
        loadChildren: './job-history/job-history.module#DemoJobHistoryModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DemoEntityModule {}
