import Question from 'Frontend/generated/com/example/application/data/Question.js';
import { dateFormatter } from 'Frontend/utils/lang.js';
import styles from './TownHallQuestion.module.scss';
import { Icon } from '@hilla/react-components/Icon.js';
import '@vaadin/icons';
import { TownHallEndpoint } from 'Frontend/generated/endpoints';
import { useState } from 'react';

export type TownHallQuestionProps = Readonly<{
  item: Question;
}>;

export default function TownHallQuestion({ item }: TownHallQuestionProps) {
  const [userVoted, setUserVoted] = useState(item.userVoted);

  return (
    <section className={styles.container}>
      <div className={styles.score}>
        {item.score}
        <Icon
          icon={userVoted ? 'vaadin:thumbs-up' : 'vaadin:thumbs-up-o'}
          onClick={(e) => {
            TownHallEndpoint.vote(item, !userVoted);
            setUserVoted(!userVoted);
          }}
        />
      </div>
      <div className={styles.text}>{item.text}</div>
      <div className={styles.date}>{dateFormatter.format(new Date(item.created))}</div>

      <div className={styles.priority}>
        <Icon icon="vaadin:chevron-up-small" />
        <div>{item.priority}</div>
        <Icon icon="vaadin:chevron-down-small" />
      </div>
    </section>
  );
}
