export const API_BASE_URL = "http://localhost:8899/jbtUserMgmt";
export const API_BASE_USER_URL = "http://localhost:8009/jbtUserMgmt";

export const API_BASE_MOVIE_URL = "http://localhost:8899/jbtMovieMgmt";
export const ACCESS_TOKEN = "accessToken";

export const OAUTH2_REDIRECT_URI = "http://localhost:3000/oauth2/redirect";

export const GOOGLE_AUTH_URL =
  API_BASE_URL + "/oauth2/authorize/google?redirect_uri=" + OAUTH2_REDIRECT_URI;
export const FACEBOOK_AUTH_URL =
  API_BASE_URL +
  "/oauth2/authorize/facebook?redirect_uri=" +
  OAUTH2_REDIRECT_URI;
export const GITHUB_AUTH_URL =
  API_BASE_URL + "/oauth2/authorize/github?redirect_uri=" + OAUTH2_REDIRECT_URI;

//intermediate url- http://localhost:3000/oauth2/redirect/{JWT}

// now in react create a small dummy apage where logic is , it takes the JWT token, verify the jwt and then save the jwt in local storage -
