import { MessageInput } from "@hilla/react-components/MessageInput.js";
import { Notification } from "@hilla/react-components/Notification.js";
import { VirtualList } from "@hilla/react-components/VirtualList.js";
import Question from "Frontend/generated/com/example/application/data/Question.js";

import { HelloReactEndpoint, TownHallEndpoint } from "Frontend/generated/endpoints.js";
import { useEffect, useState } from "react";

export default function HelloReactView() {
  const [questions, setQuestions] = useState<Question[]>([]);

  useEffect(() => {
    TownHallEndpoint.getQuestions().then(setQuestions);
  }, []);

  return (
    <>
      <section>
        <VirtualList items={questions} className="p-m">
          {({ item }) => <span>{item.text}</span>}
        </VirtualList>
      </section>

      <section>
        <MessageInput
          i18n={{
            send: "Ask",
            message: "Type your question",
          }}
          onSubmit={async (ev) => {
            const question: Question = {text: ev.detail.value, score: 0};
            console.log(question);
            await TownHallEndpoint.submitQuestion(question);
            setQuestions([...questions, question]);
          }}
        />
      </section>
    </>
  );
}
