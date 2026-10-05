import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { inject } from '@angular/core';
import { OlympicService } from './core/services/olympic.service';

export const routes: Routes = [
  {
    path: '',
    resolve: {
      olympics: () => inject(OlympicService).getOlympics(),
    },
    component: HomeComponent,
  },
  {
    path: '**', // wildcard
    component: NotFoundComponent,
  },
];
