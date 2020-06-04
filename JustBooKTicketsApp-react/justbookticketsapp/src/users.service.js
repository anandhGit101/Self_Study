import config from "config";
import { authHeader, handleResponse } from "@/_helpers";

const baseUserUrl = "http://localhost:8899/jbtUserMgmt";
export const userService = {
  getAll
};

function getAll() {
  const requestOptions = { method: "GET", headers: authHeader() };
  return fetch(`${baseUserUrl}/fetch`, requestOptions).then(handleResponse);
}
