import { Button } from '@hilla/react-components/Button.js';
import { Icon } from '@hilla/react-components/Icon.js';
import '@vaadin/icons';
import cn from 'classnames';
import Question from 'Frontend/generated/com/example/application/data/Question.js';
import { TownHallEndpoint } from 'Frontend/generated/endpoints.js';
import { dateFormatter } from 'Frontend/utils/lang.js';
import { useState } from 'react';
import styles from './TownHallQuestion.module.scss';

export type TownHallQuestionProps = Readonly<{
  item: Question;
  onPriorityChange: (priority: number) => void;
  onVote: (state: boolean) => Promise<void>;
}>;

export default function TownHallQuestion({ item, onPriorityChange, onVote }: TownHallQuestionProps) {
  const [userVoted, setUserVoted] = useState(item.userVoted);

  return (
    <section className={cn(styles.container, 'rounded-l', 'shadow-m', 'p-s', 'm-xs')}>
      <div className={styles.score}>
        <Button
          className={cn(styles.vote, 'text-l')}
          theme="icon"
          aria-label="Vote for question"
          onClick={async () => {
            await onVote(!userVoted);
            setUserVoted(!userVoted);
          }}
        >
          <Icon icon={userVoted ? 'vaadin:thumbs-up' : 'vaadin:thumbs-up-o'} />
        </Button>
        <div className={styles.number}>{item.score}</div>
      </div>
      <div className={styles.text}>{item.text}</div>
      <div className={styles.date}>{dateFormatter.format(new Date(item.created))}</div>

      <div className={styles.priority}>
        <Icon icon="vaadin:chevron-up-small" onClick={() => onPriorityChange(item.priority + 1)} />
        <div>{item.priority}</div>
        <Icon icon="vaadin:chevron-down-small" onClick={() => onPriorityChange(item.priority - 1)} />
      </div>
    </section>
  );
}
