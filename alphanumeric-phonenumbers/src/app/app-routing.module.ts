import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {PhonenumberComponent} from './phonenumber/phonenumber.component';
import {WelcomeComponent} from './welcome/welcome.component';

const routes: Routes = [
  {path: 'generator', component: PhonenumberComponent},
  {path: 'welcome', component: WelcomeComponent},
  {path: '', component: WelcomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
