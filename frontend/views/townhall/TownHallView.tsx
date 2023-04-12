import { MessageInput } from '@hilla/react-components/MessageInput.js';
import { VirtualList } from '@hilla/react-components/VirtualList.js';
import cn from 'classnames';
import { useEffect, useState } from 'react';
import Question from 'Frontend/generated/com/example/application/data/Question.js';
import { TownHallEndpoint } from 'Frontend/generated/endpoints.js';
import TownHallQuestion from 'Frontend/views/townhall/TownHallQuestion.js';
import styles from './TownHallView.module.scss';

function sortQuestions(questions: Question[]) {
  // Sort the questions by priority
  return questions.sort((a, b) => b.priority - a.priority);
}

export default function HelloReactView() {
  const [questions, setQuestions] = useState<Question[]>([]);

  useEffect(() => {
    TownHallEndpoint.getQuestions().then(setQuestions);
  }, []);

  return (
    <div className={cn('h-full', 'flex', 'flex-col', styles.container)}>
      <section className="flex-grow">
        <VirtualList items={sortQuestions(questions)} className={cn('p-m', 'h-full', 'box-border', styles.questions)}>
          {({ item: question }) => (
            <TownHallQuestion
              item={question}
              onPriorityChange={async (priority) => {
                question.priority = priority;
                setQuestions([...questions]);
                await TownHallEndpoint.setPriority(question, priority);
              }}
              onVote={async (state) => TownHallEndpoint.vote(question, state)}
            />
          )}
        </VirtualList>
      </section>

      <section>
        <MessageInput
          i18n={{
            send: 'Ask',
            message: 'Type your question',
          }}
          onSubmit={async (ev) => {
            const question: Question = {
              text: ev.detail.value,
              score: 0,
              created: new Date().toISOString(),
              userVoted: false,
              id: 0,
              priority: 0,
            };
            await TownHallEndpoint.submitQuestion(question);
            // Render the new question immediately
            setQuestions([...questions, question]);
            // Fetch the new questions from the server
            await TownHallEndpoint.getQuestions().then(setQuestions);
          }}
        />
      </section>
    </div>
  );
}
