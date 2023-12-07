import { ErrorScreen } from '~/components/common';
import TravelExploreIcon from '@mui/icons-material/TravelExplore';


export default function ErrorNotFound() {

  return (
    <ErrorScreen
      titleTab="errors.notfound.titleTab"
      title="errors.notfound.title"
      subtitle="errors.notfound.subtitle"
      Icon={TravelExploreIcon}
    />
  )
}
