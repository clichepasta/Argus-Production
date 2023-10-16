import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { AuthGuard } from './authgaurd.guard';
import { LoginService } from './login.service';

describe('AuthGuard', () => {
  let guard: AuthGuard;
  let loginService: LoginService;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      providers: [AuthGuard, LoginService],
    });
    guard = TestBed.inject(AuthGuard);
    loginService = TestBed.inject(LoginService);
    router = TestBed.inject(Router);
  });

  
});
