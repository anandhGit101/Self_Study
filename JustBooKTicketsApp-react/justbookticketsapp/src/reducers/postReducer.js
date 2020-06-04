import { ADD_MOVIE_POST, FETCH_MOVIES_POST } from "../actions/types";
//import initialState from "../reducers/state/intialState";

const initialState = { data: [] };

export default function postReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_MOVIES_POST: {
      console.log("ACTION", action);
      // return { ...state, posts: state.posts.push(action.posts) };
      return { ...state, data: action.posts };
    }

    default:
      return state;
  }
}
