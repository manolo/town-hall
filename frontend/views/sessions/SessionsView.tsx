import { Grid } from '@hilla/react-components/Grid.js';
import { GridColumn } from '@hilla/react-components/GridColumn.js';
import Session from 'Frontend/generated/com/example/application/data/entity/Session.js';
import { useEffect, useState } from 'react';
import { SessionEndpoint } from 'Frontend/generated/endpoints.js';
import Direction from 'Frontend/generated/org/springframework/data/domain/Sort/Direction.js';
import type Sort from 'Frontend/generated/dev/hilla/mappedtypes/Sort.js';


import styles from './Sessions.module.css';



export default function SessionsView() {
  const [sessions, setSessions] = useState<Session[]>([]);

  useEffect(() => {
    // SessionEndpoint.list({ pageNumber: 1, pageSize: 100, sort:{orders: []}}).then(setSessions);
    SessionEndpoint.listAll().then(setSessions);
  }, []);

  return (
    <div className="h-full flex flex-col">
      <Grid
        items={sessions}
      >
      <GridColumn path="owner" />
      <GridColumn path="description" />
      <GridColumn path="date" />
    </Grid>
    </div>
  );
}
