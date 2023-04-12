import { AppLayout } from '@hilla/react-components/AppLayout.js';
import { DrawerToggle } from '@hilla/react-components/DrawerToggle.js';
import { Item } from '@hilla/react-components/Item.js';
import { Scroller } from '@hilla/react-components/Scroller.js';
import cn from 'classnames';
import Placeholder from 'Frontend/components/placeholder/Placeholder.js';
import { MenuProps, routes, useViewMatches, ViewRouteObject } from 'Frontend/routes.js';
import { Suspense } from 'react';
import { NavLink, Outlet } from 'react-router-dom';
import styles from './MainLayout.module.scss';

type MenuRoute = ViewRouteObject &
  Readonly<{
    path: string;
    handle: Required<MenuProps>;
  }>;

export default function MenuOnLeftLayout() {
  const matches = useViewMatches();

  const currentTitle = matches[matches.length - 1]?.handle?.title ?? 'Unknown';

  const menuRoutes = (routes[0]?.children || []).filter(
    (route) => route.path && route.handle && route.handle.icon && route.handle.title,
  ) as readonly MenuRoute[];

  return (
    <AppLayout className="h-full block" primarySection="drawer">
      <header slot="drawer">
        <h1 className="text-l m-0">Vaadin Connected</h1>
      </header>
      <Scroller slot="drawer" scroll-direction="vertical">
        <nav>
          {menuRoutes.map(({ path, handle: { icon, title } }) => (
            <NavLink
              className={({ isActive }) => `${styles.navlink} ${isActive ? styles.navlink_active : ''}`}
              key={path}
              to={path}
            >
              {({ isActive }) => (
                <Item key={path} selected={isActive}>
                  <span
                    className={styles.navicon}
                    style={{ '--mask-image': `url('line-awesome/svg/${icon}.svg')` } as any}
                    aria-hidden="true"
                  ></span>
                  {title}
                </Item>
              )}
            </NavLink>
          ))}
        </nav>
      </Scroller>
      <footer slot="drawer" />

      <DrawerToggle slot="navbar" aria-label="Menu toggle"></DrawerToggle>
      <h2 slot="navbar" className="text-l m-0">
        {currentTitle}
      </h2>

      <section className={cn('h-full', styles.outlet)}>
        <Suspense fallback={<Placeholder />}>
          <Outlet />
        </Suspense>
      </section>
    </AppLayout>
  );
}
