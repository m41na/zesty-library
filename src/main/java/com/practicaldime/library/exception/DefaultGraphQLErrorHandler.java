package com.practicaldime.library.exception;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import graphql.GraphQLError;
import graphql.servlet.GenericGraphQLError;
import graphql.servlet.GraphQLErrorHandler;

public class DefaultGraphQLErrorHandler implements GraphQLErrorHandler {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultGraphQLErrorHandler.class);

	@Override
	public List<GraphQLError> processErrors(List<GraphQLError> errors) {
		final List<GraphQLError> clientErrors = filterGraphQLErrors(errors);
		if (clientErrors.size() < errors.size()) {

			// Some errors were filtered out to hide implementation - put a generic error in
			// place.
			clientErrors.add(new GenericGraphQLError("Internal Server Error(s) while executing query"));

			errors.stream().filter(error -> !isClientError(error)).forEach(error -> {
				if (error instanceof Throwable) {
					LOG.error("Error executing query!", (Throwable) error);
				} else {
					LOG.error("Error executing query ({}): {}", error.getClass().getSimpleName(), error.getMessage());
				}
			});
		}

		return clientErrors;
	}

	private boolean isClientError(GraphQLError error) {
		// TODO Auto-generated method stub
		return false;
	}

	private List<GraphQLError> filterGraphQLErrors(List<GraphQLError> errors) {
		// TODO Auto-generated method stub
		return null;
	}

}
