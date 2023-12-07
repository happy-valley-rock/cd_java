import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
  Navigate
} from "react-router-dom";
import { ErrorNotFound, ErrorScreen } from '~/components/common';
import { Layout } from '~/components/container';
import { ROUTES } from '~/consts';
import { Dashboard } from '~/views';


export default function RoutesProvider() {
  const router = createBrowserRouter(
    createRoutesFromElements(
      <Route>
        <Route element={<Layout />} errorElement={<ErrorScreen />}>
          <Route path={ROUTES.HOME} element={<Dashboard />} />
          <Route path="*" element={<ErrorNotFound />} />
        </Route>
      </Route>
    )
  );

  return (
    <RouterProvider router={router}/>
  );
}
