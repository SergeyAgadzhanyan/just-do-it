import Api from './Api';
import Text from './text';

class AuthApi extends Api {

  signUp({name, password}) {

    return fetch(Text.LinkSignUp, {
      method: 'POST',
      body:JSON.stringify({
        name,
        password,
      }),
      headers: {
        "Content-Type": "application/json",
      },
      credentials:'include'
    })
        .then(this._checkResponse);
  }

  signIn({name, password}) {
    return fetch(Text.LinkSignIn, {
      method: 'POST',
      body: new URLSearchParams({
        name,
        password,
      }),
      credentials: 'include',
    })
        .then(this._checkResponse);
  }

  checkToken() {
    return fetch(Text.LinkCheckMe, {
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'include',
    }).then(this._checkResponse);
  }
}

export default new AuthApi();
