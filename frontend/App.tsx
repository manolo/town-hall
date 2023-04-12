import router from './routes.js';
import { useState } from 'react';
import { RouterProvider } from 'react-router-dom';
import { AuthContext, initialState, useAuth } from './useAuth.js';

export default function App() {
  const [state, setState] = useState(initialState);

  return <AuthContext.Provider value={useAuth(state, setState)}>
    <RouterProvider router={router} />
  </AuthContext.Provider >;
}