package co.marcuss.acct.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    private String message;

    protected BaseResponse(BaseResponseBuilder<?, ?> b) {
        this.message = b.message;
    }

    public static BaseResponseBuilder<?, ?> builder() {
        return new BaseResponseBuilderImpl();
    }

    public static abstract class BaseResponseBuilder<C extends BaseResponse, B extends BaseResponseBuilder<C, B>> {
        private String message;

        public B message(String message) {
            this.message = message;
            return self();
        }

        public B message(String pattern, Object ... arguments) {
            this.message = MessageFormat.format(pattern, arguments);
            return self();
        }

        protected abstract B self();

        public abstract C build();

        public String toString() {
            return "BaseResponse.BaseResponseBuilder(message=" + this.message + ")";
        }
    }

    private static final class BaseResponseBuilderImpl extends BaseResponseBuilder<BaseResponse, BaseResponseBuilderImpl> {
        private BaseResponseBuilderImpl() {
        }

        protected BaseResponseBuilderImpl self() {
            return this;
        }

        public BaseResponse build() {
            return new BaseResponse(this);
        }
    }
}
