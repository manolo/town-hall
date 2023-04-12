import { Button } from '@hilla/react-components/Button.js';
import { Icon } from '@hilla/react-components/Icon.js';
import '@vaadin/icons';
import cn from 'classnames';
import Question from 'Frontend/generated/com/example/application/data/Question.js';
import { dateFormatter } from 'Frontend/utils/lang.js';
import { useState } from 'react';
import styles from './TownHallQuestion.module.scss';
import commonStyles from '../../utils/common.module.scss';

export type TownHallQuestionProps = Readonly<{
  item: Question;
  onPriorityChange: (priority: number) => void;
  onVote: (state: boolean) => Promise<void>;
}>;

export default function TownHallQuestion({ item, onPriorityChange, onVote }: TownHallQuestionProps) {
  const [userVoted, setUserVoted] = useState(item.userVoted);

  return (
    <section
      className={cn(styles.container, 'rounded-l', 'shadow-m', 'p-s', 'm-xs', item.priority > 0 && styles.emphasized)}
    >
      <div className={styles.score}>
        <Button
          className={cn(styles.vote, commonStyles.button, 'text-l')}
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
        <Button
          className={commonStyles.button}
          theme="icon"
          aria-label="Increase priority of the question"
          onClick={() => onPriorityChange(item.priority + 1)}
        >
          <Icon icon="vaadin:chevron-up-small" />
        </Button>
        <div>{item.priority}</div>
        <Button
          className={commonStyles.button}
          theme="icon"
          aria-label="Decrease priority of the question"
          onClick={() => onPriorityChange(item.priority - 1)}
        >
          <Icon icon="vaadin:chevron-down-small" />
        </Button>
      </div>
    </section>
  );
}
