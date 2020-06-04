import {
  API_BASE_URL,
  API_BASE_USER_URL,
  API_BASE_MOVIE_URL
} from "./constants";

const request = options => {
  const headers = new Headers({
    "Content-Type": "application/json"
  });

  if (localStorage.getItem("accessToken")) {
    headers.append(
      "Authorization",
      "Bearer " + localStorage.getItem("accessToken")
    );
  }
  console.log("Experience is one such", headers);
  const defaults = { headers: headers };
  options = Object.assign({}, defaults, options);
  console.log(options.url, "Options left...", options);

  return fetch(options.url, options).then(response =>
    response
      .json()
      .then(json => {
        console.log(json);
        if (!response.ok) {
          return Promise.reject(json);
        }
        return json;
      })
      .catch(err => console.error(err))
  );
};

export function getCurrentUser() {
  if (!localStorage.getItem("accessToken")) {
    return Promise.reject("No access token set.");
  }

  const getHeaders = new Headers({
    "Content-Type": "application/json",
    Authorization: "Bearer " + localStorage.getItem("accessToken")
  });
  return request({
    url: API_BASE_USER_URL + "/user/me",
    method: "GET",
    headers: getHeaders
  });
}

export function signup(signupRequest) {
  console.log(signupRequest);
  return request({
    url: API_BASE_URL + "/auth/signup",
    method: "POST",
    body: JSON.stringify(signupRequest)
  });
}

export function login(loginRequest) {
  return request({
    url: API_BASE_URL + "/auth/login",
    method: "POST",
    body: JSON.stringify(loginRequest)
  });
}

export function addMovie(addMovieRequest) {
  return request({
    url: API_BASE_MOVIE_URL + "/movie",
    method: "POST",
    body: JSON.stringify(addMovieRequest)
  });
}

export function addCinemas(addCinemasRequest) {
  return request({
    url: API_BASE_MOVIE_URL + "/cinemas",
    method: "POST",
    body: JSON.stringify(addCinemasRequest)
  });
}
