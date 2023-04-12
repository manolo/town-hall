import { Grid } from '@hilla/react-components/Grid.js';
import { GridColumn } from '@hilla/react-components/GridColumn.js';
import Vote from 'Frontend/generated/com/example/application/data/entity/Vote.js';
import { useEffect, useState } from 'react';
import { VoteEndpoint } from 'Frontend/generated/endpoints.js';
import Direction from 'Frontend/generated/org/springframework/data/domain/Sort/Direction.js';
import type Sort from 'Frontend/generated/dev/hilla/mappedtypes/Sort.js';


import styles from './Votes.module.css';



export default function VotesView() {
  const [votes, setVotes] = useState<Vote[]>([]);

  useEffect(() => {
    // VoteEndpoint.list({ pageNumber: 1, pageSize: 100, sort:{orders: []}}).then(setVotes);
    VoteEndpoint.listAll().then(setVotes);
  }, []);

  return (
    <div className="h-full flex flex-col">
      <Grid
        items={votes}
      >
      <GridColumn path="owner" />
      <GridColumn path="question.question" />
      <GridColumn path="date" />
    </Grid>
    </div>
  );
}
