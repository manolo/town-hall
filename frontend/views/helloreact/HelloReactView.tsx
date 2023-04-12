import { MessageInput } from "@hilla/react-components/MessageInput.js";
import { Notification } from "@hilla/react-components/Notification.js";
import { VirtualList } from "@hilla/react-components/VirtualList.js";

import { HelloReactEndpoint } from "Frontend/generated/endpoints.js";
import { useState } from "react";

type Question = {
  text: string;
  userVoted?: boolean;
};

export default function HelloReactView() {
  const [questions, setQuestions] = useState<Question[]>([
    { text: "What is your name?" },
    { text: "How are you?" },
  ]);

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
            setQuestions([...questions, { text: ev.detail.value }]);

            const serverResponse = await HelloReactEndpoint.sayHello(
              ev.detail.value
            );
            Notification.show(serverResponse);
          }}
        />
      </section>
    </>
  );
}
