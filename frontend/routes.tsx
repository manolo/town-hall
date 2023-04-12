import TownHallView from 'Frontend/views/townhall/TownHallView.js';
import SessionsView from 'Frontend/views/sessions/SessionsView.js';

import MainLayout from 'Frontend/views/MainLayout.js';
import { createBrowserRouter, IndexRouteObject, NonIndexRouteObject, useMatches } from 'react-router-dom';

export type MenuProps = Readonly<{
  icon?: string;
  title?: string;
}>;

export type ViewMeta = Readonly<{ handle?: MenuProps }>;

type Override<T, E> = Omit<T, keyof E> & E;

export type IndexViewRouteObject = Override<IndexRouteObject, ViewMeta>;
export type NonIndexViewRouteObject = Override<
  Override<NonIndexRouteObject, ViewMeta>,
  {
    children?: ViewRouteObject[];
  }
>;
export type ViewRouteObject = IndexViewRouteObject | NonIndexViewRouteObject;

type RouteMatch = ReturnType<typeof useMatches> extends (infer T)[] ? T : never;

export type ViewRouteMatch = Readonly<Override<RouteMatch, ViewMeta>>;

export const useViewMatches = useMatches as () => readonly ViewRouteMatch[];

export const routes: readonly ViewRouteObject[] = [
  {
    element: <MainLayout />,
    handle: { icon: 'null', title: 'Main' },
    children: [
      { path: '/', element: <TownHallView />, handle: { icon: 'comments-solid', title: 'Town Hall' } },
      { path: '/sessions', element: <SessionsView />, handle: { icon: 'calendar-check-solid', title: 'Sessions' } },

    ],
  },
];

const router = createBrowserRouter([...routes]);
export default router;
