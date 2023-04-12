import { Grid } from '@hilla/react-components/Grid.js';
import { GridColumn } from '@hilla/react-components/GridColumn.js';
import Question from 'Frontend/generated/com/example/application/data/entity/Question.js';
import { useEffect, useState } from 'react';
import { QuestionEndpoint } from 'Frontend/generated/endpoints.js';
import Direction from 'Frontend/generated/org/springframework/data/domain/Sort/Direction.js';
import type Sort from 'Frontend/generated/dev/hilla/mappedtypes/Sort.js';


import styles from './Questions.module.css';



export default function QuestionsView() {
  const [questions, setQuestions] = useState<Question[]>([]);

  useEffect(() => {
    // QuestionEndpoint.list({ pageNumber: 1, pageSize: 100, sort:{orders: []}}).then(setQuestions);
    QuestionEndpoint.listAll().then(setQuestions);

  }, []);

  return (
    <div className="h-full flex flex-col">
      <Grid
        items={questions}
      >
      <GridColumn path="question" />
      <GridColumn path="owner" />
      <GridColumn path="date" />
      <GridColumn header="session" path="session.date" />
    </Grid>
    </div>
  );
}
